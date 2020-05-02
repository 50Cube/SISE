import FileOperations

import pandas as pd
import numpy as np
import pathlib
import matplotlib.pyplot as plt
import seaborn as sns
import tensorflow as tf

from tensorflow import keras
from tensorflow.keras import layers

import tensorflow_docs as tfdocs
import tensorflow_docs.plots
import tensorflow_docs.modeling


def calculate_outputs(dataframe, threshold):
    dataframe['output'] = [1 if x < threshold else 0 for x in dataframe['distance']]


def build_model():
    # units: Positive integer, dimensionality of the output space; liczebność neuronów chyba?
    # funkcja aktywacji relu - rectified linear unit, f(z) is zero when z is less than zero and f(z) is equal to z when z is above or equal to zero.
    # input shape = liczba kolumn w dataframe
    model = keras.Sequential([
        layers.Dense(64, activation='relu', input_shape=[len(train_dataset[0].keys())]),
        layers.Dense(64, activation='relu'),
        layers.Dense(2)
    ])


    # mse - mean squared error (błąd średniokwadratowy), MSE jest wartością oczekiwaną kwadratu „błędu”, czyli różnicy między estymatorem a wartością estymowaną
    # A metric is a function that is used to judge the performance of your model. mae - mean absolute error (średni bezwzględny błąd), informuje on o ile średnio
    # w okresie prognoz, będzie wynosić odchylenie od wartości rzeczywistej. Czyli, krótko mówiąc, o jakim błędem miarowym jest obarczona nasza prognoza
    model.compile(loss='mse', optimizer = tf.keras.optimizers.RMSprop(0.001), metrics=['mae', 'mse'])
    return model


if __name__ == '__main__':
    runs = []
    train_values_measurements = []
    train_values_references = []
    outputs = []
    solution = pd.DataFrame
    solution_measurements = pd.DataFrame
    solution_references = pd.DataFrame
    train_dataset = []
    test_dataset = []
    train_dataset_labels = []
    test_dataset_labels = []

    for i in range(12):
        runs.append(FileOperations.FileOperations.load_data_from_excel
                    (r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', "measurement", "D:H"))
        # train_values_measurements.append(runs[i][['measurement x', 'measurement y']])
        # train_values_references.append(runs[i][['reference x', 'reference y']])
        train_dataset.append(runs[i].sample(frac=0.8, random_state=0))
        test_dataset.append(runs[i].drop(train_dataset[i].index))
        train_dataset_labels.append(train_dataset[i][['reference x', 'reference y']])
        test_dataset_labels.append(test_dataset[i][['reference x', 'reference y']])
        train_dataset[i] = train_dataset[i][['measurement x', 'measurement y']]
        test_dataset[i] = test_dataset[i][['measurement x', 'measurement y']]

    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar", "D:M")
    solution_measurements = solution[['measurement x', 'measurement y']].dropna()
    solution_references = solution[['reference x', 'reference y']].dropna()

    model = build_model()
    model.summary()
    # The patience parameter is the amount of epochs to check for improvement
    early_stop = keras.callbacks.EarlyStopping(monitor='val_loss', patience=10)

    # validation split - Fraction of the training data to be used as validation data
    # verbose: Integer. 0, 1, or 2. Verbosity mode. 0 = silent, 1 = progress bar, 2 = one line per epoch.
    # tfdocs.EpochDots simply prints a . for each epoch, and a full set of metrics every 100 epochs.
    # EarlyStopping callback tests a training condition for every epoch. If a set amount of epochs elapses without showing improvement, then it stops the training
    history = model.fit(train_dataset[0], train_dataset_labels[0], epochs=20, validation_split=0.2, verbose=0, callbacks=[early_stop, tfdocs.modeling.EpochDots()])
    hist = pd.DataFrame(history.history)
    hist['epoch'] = history.epoch
    hist.tail()

    test_predictions = model.predict(test_dataset[0])
    test_predictions_dataframe = pd.DataFrame(test_predictions, columns=['x', 'y'])

    solution_predictions = model.predict(solution_measurements)
    solution_predictions_dataframe = pd.DataFrame(solution_predictions, columns=['x', 'y'])

    sns.relplot(x='x', y='y', data=solution_predictions_dataframe)
    sns.relplot(x='reference x', y='reference y', data=solution_references)
    sns.relplot(x='measurement x', y='measurement y', data=solution_measurements)
    plt.show()

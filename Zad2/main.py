import FileOperations

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import tensorflow as tf

from tensorflow import keras
from tensorflow.keras import layers

import tensorflow_docs as tfdocs
import tensorflow_docs.modeling


def build_model():
    # units: Positive integer, dimensionality of the output space; liczebność neuronów chyba?
    # funkcja aktywacji relu - rectified linear unit, f(z) is zero when z is less than zero and f(z) is equal to z when z is above or equal to zero.
    # input shape = liczba kolumn w dataframe
    model = keras.Sequential([
        layers.Dense(8, activation='relu', input_shape=[len(train_dataset_measurement[0].keys())]),
        layers.Dense(16, activation='relu'),
        layers.Dense(32, activation='relu'),
        layers.Dense(64, activation='relu'),
        layers.Dense(2)
    ])


    # mse - mean squared error (błąd średniokwadratowy), MSE jest wartością oczekiwaną kwadratu „błędu”, czyli różnicy między estymatorem a wartością estymowaną
    # A metric is a function that is used to judge the performance of your model. mae - mean absolute error (średni bezwzględny błąd), informuje on o ile średnio
    # w okresie prognoz, będzie wynosić odchylenie od wartości rzeczywistej. Czyli, krótko mówiąc, o jakim błędem miarowym jest obarczona nasza prognoza
    model.compile(loss='mse', optimizer = tf.keras.optimizers.RMSprop(0.001), metrics=['mae', 'mse'])
    return model


if __name__ == '__main__':
    solution = pd.DataFrame
    solution_measurements = pd.DataFrame
    solution_references = pd.DataFrame
    train_dataset_measurement = []
    train_dataset_reference = []
    train_datasets_measurement = pd.DataFrame
    train_datasets_reference = pd.DataFrame

    for i in range(12):
        train_dataset_measurement.append(FileOperations.FileOperations.load_data_from_excel
                    (r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', "measurement", "D:H"))
        train_dataset_reference.append(train_dataset_measurement[i][['reference x', 'reference y']])
        train_dataset_measurement[i] = train_dataset_measurement[i][['measurement x', 'measurement y']]

    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar", "D:M")
    solution_measurements = solution[['measurement x', 'measurement y']].dropna()
    solution_references = solution[['reference x', 'reference y']].dropna()

    train_datasets_measurement = pd.concat(train_dataset_measurement, ignore_index=True)
    train_datasets_reference = pd.concat(train_dataset_reference, ignore_index=True)

    model = build_model()
    # model.summary()

    # validation split - Fraction of the training data to be used as validation data
    # verbose: Integer. 0, 1, or 2. Verbosity mode. 0 = silent, 1 = progress bar, 2 = one line per epoch.
    # tfdocs.EpochDots simply prints a . for each epoch, and a full set of metrics every 100 epochs.
    # EarlyStopping callback tests a training condition for every epoch. If a set amount of epochs elapses without showing improvement, then it stops the training
    history = model.fit(train_datasets_measurement, train_datasets_reference, epochs=1000, validation_split=0.1, verbose=0, callbacks=[tfdocs.modeling.EpochDots()])

    solution_predictions = model.predict(solution_measurements)
    solution_predictions_dataframe = pd.DataFrame(solution_predictions, columns=['x', 'y'])

    sns.relplot(x='x', y='y', data=solution_predictions_dataframe)
    sns.relplot(x='reference x', y='reference y', data=solution_references)
    sns.relplot(x='measurement x', y='measurement y', data=solution_measurements)
    plt.show()

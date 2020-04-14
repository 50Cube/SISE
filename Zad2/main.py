import FileOperations
import pandas as pd
import neurolab as nl


def calculate_outputs(dataframe, threshold):
    dataframe['output'] = [1 if x < threshold else 0 for x in dataframe['distance']]


if __name__ == '__main__':
    runs = []
    train_values = []
    outputs = []
    solution = pd.DataFrame
    for i in range(12):
        runs.append(FileOperations.FileOperations.load_data_from_excel
                    (r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', "measurement", "D:H"))
        x1 = runs[i]['measurement x']
        x2 = runs[i]['reference x']
        y1 = runs[i]['measurement y']
        y2 = runs[i]['reference y']
        runs[i]['distance'] = pow(((x1 - x2)**2 + (y1 - y2)**2), 0.5)
        calculate_outputs(runs[i], 200)
        train_values.append(runs[i][['measurement x', 'measurement y']])
        outputs.append(runs[i][['output']])
    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar", "D:M")
    print(runs)

    # liczba par punktów - liczba wejść
    # pierwszy argument pary - minimalna wartosc liczby wejsciowej
    # drugi argument pary - maksymalna wartosc liczby wejsciowej
    # 1 - liczba neuronów
    net = nl.net.newp([[-500, 7000], [-1000, 5000]], 1)

    # epochs - liczba cykli
    # show - co ile cykli wypisać na terminal
    # lr - learning rate, step size that`s taken to adjust the parameters so that we move towards the end goal
    error = net.train(train_values[0].values.tolist(), outputs[0].values.tolist(), epochs=20, show=5, lr=0.5)

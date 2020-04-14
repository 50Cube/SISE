import FileOperations
import pandas as pd
import neurolab as nl

if __name__ == '__main__':
    runs = []
    train_values = []
    train_targets = []
    solution = pd.DataFrame
    for i in range(12):
        runs.append(FileOperations.FileOperations.load_data_from_excel
                    (r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', "measurement", "D:H"))
        train_values.append(runs[i][['measurement x', 'measurement y']])
        train_targets.append(runs[i][['reference x', 'reference y']])
    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar", "D:M")
    test_value = solution[['measurement x', 'measurement y']].head(1540)
    test_target = solution[['reference x', 'reference y']].head(1540)

    net = nl.net.newp([[-1, 2], [0, 2]], 1)
    print(train_values[0].values.tolist())
    print()
    print(train_targets[0].values.tolist())


    #nie wiem czemu to działa
    train = [[0,0], [0,1]]
    train_target = [[0.0], [0.1]]
    error = net.train(train, train_target, epochs=2, show=10, lr=0.1)
    print(train)


    # a to się kopci

    # error = net.train(train_values[0].values.tolist(), train_targets[0].values.tolist(), epochs=2, show=10, lr=0.1)
    # out = net.sim(test_value)
    # print(out)

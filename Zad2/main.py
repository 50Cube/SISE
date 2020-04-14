import FileOperations
import pandas as pd

if __name__ == '__main__':
    runs = []
    solution = pd.DataFrame
    for i in range(12):
        runs.append(FileOperations.FileOperations.load_data_from_excel
                    (r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', "measurement", "D:H"))
    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar", "D:M")
    print(runs)
    print(solution)

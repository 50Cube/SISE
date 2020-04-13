import FileOperations
import pandas as pd

if __name__ == '__main__':
    runs = []
    solution = pd.DataFrame
    for i in range(12):
        FileOperations.FileOperations.load_data_from_excel_list(r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', runs, "measurement")
    solution = FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', "pomiar")
    # print(runs)
    print(solution)

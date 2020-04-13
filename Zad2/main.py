import FileOperations

if __name__ == '__main__':
    runs = []
    solution = []
    for i in range(12):
        FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_dane_pomiarowe\pozyxAPI_only_localization_measurement' + str(i+1) + '.xlsx', runs, "measurement")
    FileOperations.FileOperations.load_data_from_excel(r'pozyxAPI_only_localization_dane_testowe_i_dystrybuanta.xlsx', solution, "pomiar")
    print (runs)
    print(solution)

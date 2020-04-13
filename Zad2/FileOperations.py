import pandas as pd


class FileOperations:

    def __init__(self):
        pass

    @staticmethod
    def load_data_from_excel_list(path, collection, sheet):
        df = pd.read_excel(path, sheet_name=sheet, usecols="D:H")
        collection.append(df)

    @staticmethod
    def load_data_from_excel(path, sheet):
        dataframe = pd.read_excel(path, sheet_name=sheet, usecols="D:H")
        return dataframe


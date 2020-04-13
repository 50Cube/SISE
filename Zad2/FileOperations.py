from pandas import DataFrame, read_csv
import pandas as pd


class FileOperations:

    @staticmethod
    def load_data_from_excel(path):
            df = DataFrame
            df = pd.read_excel(path, sheet_name='measurement')
            print(df)


from utils.prase_data import get_data
from utils.prepare_data_ops import get_distinct_letters
import controller.Controller

class UI:
    def __init__(self, controller):
        self.__controller = controller
        self.data = get_data("/Users/diananoveanu/School/criptAritmethic/files/in_file.txt")
        self.letters = get_distinct_letters(self.data)

    def menu(self):
        print("Choose the type of way you want to solve the criptarithmenic puzzle:\n")
        print("1. GBFS.")
        print("2. DFS.")
        print("0. exit")

    def run(self):
        while(True):
            self.menu()
            cmd = int(input())
            if (cmd == 1):
                rez = solve_gbfs(self.letters, self.data)
                print(rez)
            elif (cmd == 2):
                rez = solve_dfs(self.letters, self.data)
                print(rez)
            elif (cmd == 0):
                print("exit...")
                break
            else:
                print("Invalid command.")


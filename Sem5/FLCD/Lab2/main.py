from utils import read_fa_from_file

path_to_fa = '/Users/diananoveanu/UBB/Sem5/FLCD/Lab2/fa_input.txt'

fa = read_fa_from_file(path_to_fa)

print(fa.get_alphabet())
print(fa.get_states())
print(fa.get_final_states())
print(fa.get_transitions())
print(fa.get_start_state())
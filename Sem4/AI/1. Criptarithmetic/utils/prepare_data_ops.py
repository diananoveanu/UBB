
def get_distinct_letters(data):
    """
    Function to get a list containing the distinct letters from the data
    :param data:
    :return: a list of letters
    """
    dist_letters = []
    for word in data.word_lst:
        for letter in word:
            if letter not in dist_letters:
                dist_letters.append(letter)
    for letter in data.result:
        if letter not in dist_letters:
            dist_letters.append(letter)
    return dist_letters

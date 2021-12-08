def abstractmethod(func):
    """A decorator indicating a method is abstract method"""

    def wrap(self, *args, **kwargs):
        raise NotImplementedError(f"{self.__class__.__name__}.{func.__name__} is not implemented!")

    return wrap


def get_data(fn, split=False, split_char=None):
    """
    :param fn: filename to open
    :param split: if you want to split the data read
    :param split_char: character you want to split the data on
    Example:
    >>>data = get_data('file.txt', split=True, split_char=",")
    >>>print(data)
    [1, 2, 3, 4]
    """
    with open(fn) as f:
        data = f.read()
        if split:
            if split_char:
                data = data.split(split_char)
    return data

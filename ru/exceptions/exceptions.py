class ExceptionBase(Exception):
    """
    Base Exception class for all exception classes
    """


class ConfigKeyError(ExceptionBase):
    """
    KeyError for config class
    """


class ConfigFileNotFoundError(ExceptionBase):
    """
    FileNotFoundError for config class
    """

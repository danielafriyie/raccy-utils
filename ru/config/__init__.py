import json

from ru.utils import abstractmethod, get_data
from ru.exceptions.exceptions import ConfigKeyError, ConfigFileNotFoundError


class BaseConfig:
    """"
    Base config class for all config classes
    """
    CONFIG_PATH = None

    def __init__(self):
        self._config = self.load()

    @property
    def config(self):
        return self._config

    @abstractmethod
    def save(self, *args, **kwargs):
        pass

    @abstractmethod
    def load_config(self, *args, **kwargs):
        pass

    def get(self, item, default=None, cast=None):
        try:
            val = self._config[item]
            if cast:
                if cast is bool:
                    return eval(val.strip().capitalize())
                return cast(val.strip())
            return val
        except KeyError:
            return default

    def load(self):
        try:
            return self.load_config()
        except FileNotFoundError:
            raise ConfigFileNotFoundError(f"{self.__class__.__name__}: Config file '{self.CONFIG_PATH}' not found!")

    def __getitem__(self, item):
        try:
            return self._config[item]
        except KeyError:
            raise ConfigKeyError(f"{item}")

    def __setitem__(self, key, value):
        self._config[key] = value

    def __repr__(self):
        return str(self._config)


class JsonConfig(BaseConfig):
    CONFIG_PATH = 'config.json'

    def load_config(self):
        with open(self.CONFIG_PATH) as f:
            config = json.load(f)
        return config

    def save(self, filename='config.json'):
        with open(filename, 'w') as f:
            json.dump(self._config, f)


class TextConfig(BaseConfig):
    CONFIG_PATH = 'config.txt'

    def load_config(self):
        config = {}
        data = get_data(self.CONFIG_PATH, split=True, split_char='\n')
        for d in data:
            try:
                key, val = d.split('=')
                config[key.strip()] = val
            except ValueError:
                pass
        return config

    def save(self, filename='config.txt'):
        with open(filename, 'w') as f:
            len_config = len(self._config) - 1
            for idx, key in enumerate(self._config):
                f.write(f"{key}={self._config[key]}")
                if idx < len_config:
                    f.write('\n')

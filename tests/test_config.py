import unittest
import os
import sys
import json

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(BASE_DIR)

from ru.config import JsonConfig, TextConfig
from ru.exceptions.exceptions import ConfigFileNotFoundError, ConfigKeyError


class TestConfig(unittest.TestCase):

    def test_invalid_config_path(self):
        with self.assertRaises(ConfigFileNotFoundError):
            TextConfig()

        with self.assertRaises(ConfigFileNotFoundError):
            JsonConfig()

    def test_config_keyerror(self):
        with open('config.json', 'w') as f:
            config_dict = {}
            json.dump(config_dict, f)

        jconfig = JsonConfig()
        with self.assertRaises(ConfigKeyError):
            foo = jconfig['foo']

        with open('config.txt', 'w') as f:
            f.write('')

        tconfig = TextConfig()
        with self.assertRaises(ConfigKeyError):
            bar = tconfig['bar']

        os.remove('config.txt')
        os.remove('config.json')

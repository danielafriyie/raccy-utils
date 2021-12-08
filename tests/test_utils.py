import unittest
import os
import sys

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(BASE_DIR)

from ru.utils import abstractmethod


class TestUtils(unittest.TestCase):

    def test_abstractmethod(self):
        class Foo:

            @abstractmethod
            def bar(self):
                pass

        with self.assertRaises(NotImplementedError):
            Foo().bar()

import unittest
import os
import sys

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.append(BASE_DIR)

from ru import logger


class TestLogger(unittest.TestCase):

    def test_log_path(self):
        log_path = os.path.join('logs', 'foo.log')
        foo_log = logger(filename='foo')
        self.assertTrue(os.path.exists(log_path))

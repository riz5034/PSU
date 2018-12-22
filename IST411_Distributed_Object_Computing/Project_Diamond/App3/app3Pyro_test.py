import unittest,Pyro4
from app3Pyro import App3Pyrp

class TestApp3Pyro(unittest.TestCase):
    '''
    Testing Pyro
    '''
    def test_pyro(self):
        app3pyrotest= App3Pyrp()
        self.assertTrue(app3pyrotest.pyro_payload())

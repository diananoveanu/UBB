import os

basedir = os.path.abspath(os.path.dirname(__file__))


class Config(object):

    SECRET_KEY = os.environ.get('SECRET_KEY') or 'zxmqiowejksdnc18923789123jksnmcasdl128wduihsaj'

    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL') or \
                              'sqlite:///' + os.path.join(basedir, 'meal.db')
    SQLALCHEMY_TRACK_MODIFICATIONS = False

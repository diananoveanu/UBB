from app import db


class Meal(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    mealDescription = db.Column(db.String(64), index=True, unique=False)
    mealCount = db.Column(db.Integer, index=True, unique=False)

    def __repr__(self):
        return '<Meal {}>'.format(self.description)

import binascii
import json
import os

from flask import request, jsonify, Response, url_for, redirect

from app import app, db
from app.models import Meal


def generate_token():
    return binascii.hexlify(os.urandom(92)).decode()


@app.route('/api/item/', methods=['GET', 'POST'])
def get_items():
    if request.method == 'GET':
        meals = Meal.query.all()
        body = []
        for meal in meals:
            body.append({'id': meal.id, 'mealCount': meal.mealCount, 'mealDescription': meal.mealDescription})
        body = json.dumps(body)
        return Response(body, status=201, mimetype='application/json')
    elif request.method == 'POST':
        data = json.loads(request.data)
        m = Meal(mealDescription=data['mealDescription'], mealCount=int(data['mealCount']))
        db.session.add(m)
        db.session.commit()
        meals = Meal.query.all()
        for m in meals:
            if m.mealDescription == data['mealDescription'] and m.mealCount == int(data['mealCount']):
                break
        meal = m
        return Response(
            json.dumps({'id': meal.id, 'mealCount': meal.mealCount, 'mealDescription': meal.mealDescription}),
            status=201, mimetype='application/json')


@app.route('/api/item/<id>', methods=['GET', 'POST', 'PUT'])
def edit_item(id):
    if request.method == 'POST' or request.method == 'PUT':
        meal = db.session.query(Meal).filter_by(id=id).first()
        data = json.loads(request.data)
        print(data)
        meal.mealCount = int(data['mealCount'])
        meal.mealDescription = data['mealDescription']
        db.session.commit()
        meals = Meal.query.all()
        body = []
        for meal in meals:
            body.append({'id': meal.id, 'mealCount': meal.mealCount, 'mealDescription': meal.mealDescription})
        body = json.dumps(body)
        return Response(body, status=200, mimetype='application/json')


@app.route('/api/item/del/<id>', methods=['POST', 'DELETE'])
def del_item(id):
    if request.method == 'POST' or request.method == 'DELETE':
        Meal.query.filter(Meal.id == id).delete()
        db.session.commit()
        meals = Meal.query.all()
        body = []
        for meal in meals:
            body.append({'id': meal.id, 'mealCount': meal.mealCount, 'mealDescription': meal.mealDescription})
        body = json.dumps(body)
        return Response(body, status=204, mimetype='application/json')

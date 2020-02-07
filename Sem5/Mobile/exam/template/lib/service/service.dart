import 'package:template/domain/IObject.dart';
import 'package:template/repository/ObjectRepository.dart';

abstract class ObjectService<T extends IObject> {
  ObjectRepository<T> _repository;

  ObjectService(ObjectRepository repo) {
    this._repository = repo;
  }

  Future<IObject> addObject(T t) {
    return _repository.add(t);
  }

  Future<int> deleteObject(int id) {
    return _repository.deleteObject(id);
  }

  Future<int> editObject(int id, T t) {
    return _repository.editObject(id, t);
  }

  Future<IObject> getObject(int id) {
    return _repository.getObject(id);
  }

  Future<List<IObject>> getObjects() {
    return _repository.getObjects();
  }

  Future<int> size() {
    return _repository.size();
  }
}
import 'dart:async';
import 'dart:convert';

import 'package:flutter/http.dart' as http;
import 'model_lib.dart';

class ContactRepositoryRandom implements ContactRepository {
  static const _kRandomUserUrl = 'http://api.randomuser.me/?results=15';
  final JsonDecoder _decoder = new JsonDecoder();

  @override
  Future<List<Contact>> fetch(){
    return http.get(_kRandomUserUrl)
       .then((http.Response response) {
         final String jsonBody = response.body;
         final statusCode = response.statusCode;

         if(statusCode < 200 || statusCode >= 300 || jsonBody == null) {
           throw new FetchDataException("Error while getting contacts [StatusCode:$statusCode, Error:${response.error}]");
         }

         final contactsContainer = _decoder.convert(jsonBody);
         final List contactItems = contactsContainer['results'];

         return contactItems.map( (contactRaw) => new Contact.fromMap(contactRaw) )
                        .toList();
     });
 }
}

class FetchDataException implements Exception {
  String _message;

  FetchDataException(this._message);

  String toString() {
    return "Exception: $_message";
  }
}

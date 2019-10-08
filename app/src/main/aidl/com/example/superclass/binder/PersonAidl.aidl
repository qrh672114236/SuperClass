
package com.example.superclass.binder;

import com.example.superclass.binder.Person;

interface PersonAidl {

    void addPerson(inout Person person);

    List<Person> getPersonList();
}

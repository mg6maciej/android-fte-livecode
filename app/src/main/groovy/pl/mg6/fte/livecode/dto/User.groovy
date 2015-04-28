package pl.mg6.fte.livecode.dto

import groovy.transform.CompileStatic

@CompileStatic
final class User {

    String name
    String company
    String location

    String getFullInfo() {
        return [name, company, location].findAll().join(', ')
    }
}

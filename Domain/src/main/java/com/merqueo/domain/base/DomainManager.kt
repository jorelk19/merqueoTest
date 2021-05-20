package com.merqueo.domain.base

abstract class DomainManager<T> : DomainBase() {
    abstract fun domainResult(element : T)
}
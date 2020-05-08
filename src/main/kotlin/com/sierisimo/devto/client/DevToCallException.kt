package com.sierisimo.devto.client

class DevToCallException(val devToError: DevToError) : RuntimeException(devToError.error)
#!/bin/bash

test$(curl localhost:8766/sum?a=12&b=35) -eq 6


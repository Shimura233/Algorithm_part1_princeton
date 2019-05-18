#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat May 18 10:30:02 2019

@author: shimura
"""

def alice_play(n):
    if n==0:
        print('bob wins')
    else:
        bob_play(n-1)

def bob_play(n):
    if n==0:
        print('alice wins')
    elif n%2==0:
        alice_play(n-2)
    else:
        alice_play(n-1)
        
alice_play(20)
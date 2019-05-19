#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat May 18 10:30:02 2019

@author: shimura
"""

def alice_play(n):
    """alice's strategy"""
    if n==0:
        print('bob wins')
    else:
        bob_play(n-1)

def bob_play(n):
    """bob's strategy"""
    if n==0:
        print('alice wins')
    elif n%2==0:
        alice_play(n-2)
    else:
        alice_play(n-1)
        
alice_play(20)

def num_partition(n,m):
    """calculate the number of partitions using intergers up to m to represent n
    
    >>> num_partition(6,4)
    9
    >>> num_partition(5,5)
    7
    >>> num_partition(10,10)
    42
    >>> num_partition(15,15)
    176
    >>> num_partition(20,20)
    627
    """
    if n==0:
        return 1
    elif n<0:
        return 0
    elif m==1:
        return 1
    else:
        return num_partition(n,m-1)+num_partition(n-m,m)

def num_partition2(n,m):
    """calculate the number of partitions using intergers up to m to represent n
    
    >>> num_partition2(6,4)
    9
    >>> num_partition2(5,5)
    7
    >>> num_partition2(10,10)
    42
    >>> num_partition2(15,15)
    176
    >>> num_partition2(20,20)
    627
    """
    num=0
    if n==0:
        return 1
    elif n<0:
        return 0
    else:
        for i in range(m):
            num +=num_partition2(n-i-1,i+1)
    return num

def make_func_repeater(f,x):
    """
    
    >>> incr_1=make_func_repeater(lambda x:x+1,1)
    >>> incr_1(2) #same as f(f(x))
    3
    >>> incr_1(5)
    6
    """
    
    def repeat(n):
        if n==0:
            return x
        else:
            return f(repeat(n-1))
    return repeat
        
def count_k(n,k):
    """n stairs, can take up to k steps at a time
    
    >>> count_k(3,3)
    4
    >>> count_k(4,4)
    8
    >>> count_k(10,3)
    274
    >>> count_k(300,1)
    1
    """
    if n<0:
        return 0
    elif n==0:
        return 1
    else:
        num=0
        for i in range(k):
            num +=count_k(n-i-1,k)
        return num
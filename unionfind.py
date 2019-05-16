#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed May 15 10:41:22 2019

@author: shimura
"""

class unionfind:
    def __init__(self,N):
        self.id=[]
        self.maxvalue=[]
        self.size=[]
        for i in range(N):
            self.id.append(i)
            self.maxvalue.append(i)
            self.size.append(1)
    def root(self,i):
        while self.id[i] != i:
            i=self.id[i]
        return i
    def union(self,i,j):
        root1=self.root(i)
        root2=self.root(j)
        if self.size[root1]<self.size[root2]:
            self.id[root1]=root2
            self.size[root2]+=self.size[root1]
            self.maxvalue[root2]=max(self.maxvalue[root1],self.maxvalue[root2])
        else:
            self.id[root2]=root1
            self.size[root1]+=self.size[root2]
            self.maxvalue[root1]=max(self.maxvalue[root1],self.maxvalue[root2])
    def connected(self,i,j):
        return self.root(i)==self.root(j)

class unionfindtoremove: #interview quiz 3
    def __init__(self,N):
        self.id=[]
        self.maxvalue=[]
        self.size=[]
        for i in range(N):
            self.id.append(i)
            self.maxvalue.append(i)
            self.size.append(1)
    def _root(self,i):
        while self.id[i] != i:
            i=self.id[i]
        return i
    def _union(self,i,j):
        root1=self._root(i)
        root2=self._root(j)
        if self.size[root1]<self.size[root2]:
            self.id[root1]=root2
            self.size[root2]+=self.size[root1]
            self.maxvalue[root2]=max(self.maxvalue[root1],self.maxvalue[root2])
        else:
            self.id[root2]=root1
            self.size[root1]+=self.size[root2]
            self.maxvalue[root1]=max(self.maxvalue[root1],self.maxvalue[root2])
    def remove(self,i):
        self._union(i,i+1)
    def successor(self,i):
        root1=self._root(i+1)
        return self.maxvalue[root1]
    
b=unionfindtoremove(8)


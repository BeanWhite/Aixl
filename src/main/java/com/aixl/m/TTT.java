package com.aixl.m;

import java.util.List;

public class TTT<T> {

    public void test(List<T> t){

        for(int i=0;i<t.size();i++){
            System.out.println(t.get(i));
        }
    }
}

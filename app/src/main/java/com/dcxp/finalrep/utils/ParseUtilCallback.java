package com.dcxp.finalrep.utils;

import com.parse.ParseObject;

import com.parse.ParseException;

/**
 * Created by dannyflax on 7/21/15.
 */
public interface ParseUtilCallback{
    public void success(Object result);
    public void failure(Object reason);
}

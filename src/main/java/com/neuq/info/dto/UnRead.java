package com.neuq.info.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neuq.info.entity.Comment;
import com.neuq.info.entity.Like;
import com.neuq.info.entity.Post;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Date;
import java.util.List;

/**
 * Created by lihang on 2017/4/27.
 */
public class UnRead implements Comparable<UnRead>{
        @JsonIgnore
        protected Date date;
        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int compareTo(UnRead o) {
            if(this.getDate().getTime()>=o.getDate().getTime()) {
                return -1;
            }
            else {
                return 1;
            }
        }



}
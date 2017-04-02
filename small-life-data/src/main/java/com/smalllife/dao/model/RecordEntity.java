package com.smalllife.dao.model;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Aaron on 02/04/2017.
 */
@Setter
@Getter
@Entity(value = "Record", noClassnameStored = true)
public class RecordEntity {
}

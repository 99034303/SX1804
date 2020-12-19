package com.bw.phonecontact.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class MyEntity extends SectionEntity<ContactEntity> {
    public MyEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyEntity(ContactEntity contactEntity) {
        super(contactEntity);
    }
}

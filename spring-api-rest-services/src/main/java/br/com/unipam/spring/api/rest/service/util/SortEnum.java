package br.com.unipam.spring.api.rest.service.util;


import org.springframework.data.domain.Sort;

public enum SortEnum {
    DATE_ASC("date", Sort.Direction.ASC), DATE_DESC("date", Sort.Direction.DESC), VALUE_ASC("value", Sort.Direction.ASC), VALUE_DESC("value", Sort.Direction
            .DESC);

    private String value;
    private Sort.Direction direction;

    SortEnum(String value, Sort.Direction direction) {
        this.value = value;
        this.direction = direction;
    }

    public SortEnum getSortEnum(String name) {
        for (SortEnum sortEnum : SortEnum.values()) {
            if (sortEnum.equals(name)) {
                return sortEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public Sort.Direction getDirection() {
        return direction;
    }
}

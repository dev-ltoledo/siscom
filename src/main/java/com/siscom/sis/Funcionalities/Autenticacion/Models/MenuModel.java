package com.siscom.sis.Funcionalities.Autenticacion.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuModel {
    private Integer id;
    private String menuName;
    private String menuLink;
    private String iconClass;
    private boolean isActive;
    private Integer parentId;
    private Integer menuOrder; // Nuevo atributo
    private Integer idEstado;   // Nuevo atributo

    public MenuModel(Integer id, String menuName, String menuLink, String iconClass, boolean isActive, Integer parentId, Integer menuOrder, Integer idEstado) {
        this.id = id;
        this.menuName = menuName;
        this.menuLink = menuLink;
        this.iconClass = iconClass;
        this.isActive = isActive;
        this.parentId = parentId;
        this.menuOrder = menuOrder;
        this.idEstado = idEstado;
    }
}

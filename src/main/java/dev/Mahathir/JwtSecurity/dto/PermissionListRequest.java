package dev.Mahathir.JwtSecurity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissionListRequest {
    List<Integer> permissionIdList;
}

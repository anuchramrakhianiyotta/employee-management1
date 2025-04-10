package com.example.employee_management.dto;

public class UserDTO
{
    private String username;
    private String roleName;

    public UserDTO(String username, String roleName)
    {
        this.username = username;
        this.roleName = roleName;
    }

    public String getUsername()
    {
        return username;
    }

    public String getRoleName()
    {
        return roleName;
    }
}

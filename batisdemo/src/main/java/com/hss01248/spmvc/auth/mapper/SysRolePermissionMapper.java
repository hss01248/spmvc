package com.hss01248.spmvc.auth.mapper;

import com.hss01248.spmvc.auth.po.SysRolePermission;
import com.hss01248.spmvc.auth.po.SysRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionMapper {
    long countByExample(SysRolePermissionExample example);

    int deleteByExample(SysRolePermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    List<SysRolePermission> selectByExample(SysRolePermissionExample example);

    SysRolePermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}
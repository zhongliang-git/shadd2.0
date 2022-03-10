-- 菜单 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('1bca262678284bc58e0c91d762bf9b4e', 'tFactory', '厂家', '20', 'tFactory', 'shadd/factory/index', 1, 0, 'C', 0, 'shadd:tFactory:list', 'chart', 'b4032f6bf8b5460e9e29111c87459a33', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e', '20', '000100/20', '2', 'n', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

-- 按钮 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('237239d0ed2b491d9689ac484bf2dc4a', 'tFactoryQuery', '厂家查询', '10', '#', '', 1, 0, 'F', 0, 'shadd:tFactory:query', '#', '1bca262678284bc58e0c91d762bf9b4e', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e/237239d0ed2b491d9689ac484bf2dc4a', '10', '000100/20/10', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('2e2c8cba6c8c47ed8c67b2b3c4e0568f', 'tFactoryAdd', '厂家新增', '20', '#', '', 1, 0, 'F', 0, 'shadd:tFactory:add', '#', '1bca262678284bc58e0c91d762bf9b4e', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e/2e2c8cba6c8c47ed8c67b2b3c4e0568f', '20', '000100/20/20', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('228b6da3efd04f34a8567959add20837', 'tFactoryEdit', '厂家修改', '30', '#', '', 1, 0, 'F', 0, 'shadd:tFactory:edit', '#', '1bca262678284bc58e0c91d762bf9b4e', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e/228b6da3efd04f34a8567959add20837', '30', '000100/20/30', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('6cbd4431e3fe4af2878720c6c5ba075e', 'tFactoryRemove', '厂家删除', '10', '#', '', 1, 0, 'F', 0, 'shadd:tFactory:remove', '#', '1bca262678284bc58e0c91d762bf9b4e', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e/6cbd4431e3fe4af2878720c6c5ba075e', '40', '000100/20/40', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('bf110882c904409fa7851fcb562db516', 'tFactoryExport', '厂家查询', '50', '#', '', 1, 0, 'F', 0, 'shadd:tFactory:export', '#', '1bca262678284bc58e0c91d762bf9b4e', 'b4032f6bf8b5460e9e29111c87459a33/1bca262678284bc58e0c91d762bf9b4e/bf110882c904409fa7851fcb562db516', '50', '000100/20/50', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');


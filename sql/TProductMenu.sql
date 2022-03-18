-- 菜单 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('8446b256b2be48d2b0bf70b184cd5804', 'tProduct', '产品', '10', 'tProduct', 'shadd/tproduct/index', 1, 0, 'C', 0, 'shadd:tProduct:list', 'link', '55445efde2034710ba9ba4df81a9ae2c', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804', '10', '000110/10', '2', 'n', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

-- 按钮 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('5c6daee27b1b40b092fc6b7de1bf1883', 'tProductQuery', '产品查询', '10', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:query', '#', '8446b256b2be48d2b0bf70b184cd5804', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804/5c6daee27b1b40b092fc6b7de1bf1883', '10', '000110/10/10', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('757f09313c7d4cc4b35dfbacb09ba11c', 'tProductAdd', '产品新增', '20', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:add', '#', '8446b256b2be48d2b0bf70b184cd5804', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804/757f09313c7d4cc4b35dfbacb09ba11c', '20', '000110/10/20', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('6699e8e3f82e484ab8e643ce2c595b22', 'tProductEdit', '产品修改', '30', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:edit', '#', '8446b256b2be48d2b0bf70b184cd5804', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804/6699e8e3f82e484ab8e643ce2c595b22', '30', '000110/10/30', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('f65564f11cc34d629f75e39f81ebd057', 'tProductRemove', '产品删除', '10', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:remove', '#', '8446b256b2be48d2b0bf70b184cd5804', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804/f65564f11cc34d629f75e39f81ebd057', '40', '000110/10/40', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('18c8ef82cd434787b4471289119a82d2', 'tProductExport', '产品查询', '50', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:export', '#', '8446b256b2be48d2b0bf70b184cd5804', '55445efde2034710ba9ba4df81a9ae2c/8446b256b2be48d2b0bf70b184cd5804/18c8ef82cd434787b4471289119a82d2', '50', '000110/10/50', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');


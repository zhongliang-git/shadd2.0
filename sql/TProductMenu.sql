-- 菜单 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('236a07ff8be2400ea877507826aefae0', 'tProduct', '产品', '10', 'tProduct', 'shadd/tproduct/index', 1, 0, 'C', 0, 'shadd:tProduct:list', 'link', '55445efde2034710ba9ba4df81a9ae2c', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0', '10', '000110/10', '2', 'n', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

-- 按钮 SQL
insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('627ab2ee313547cbb5fbe351a850ded0', 'tProductQuery', '产品查询', '10', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:query', '#', '236a07ff8be2400ea877507826aefae0', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0/627ab2ee313547cbb5fbe351a850ded0', '10', '000110/10/10', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('0015af0f6e3440698596cf5c7b05c78e', 'tProductAdd', '产品新增', '20', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:add', '#', '236a07ff8be2400ea877507826aefae0', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0/0015af0f6e3440698596cf5c7b05c78e', '20', '000110/10/20', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('e4ae5cff26294126bfa9cacaeccb9016', 'tProductEdit', '产品修改', '30', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:edit', '#', '236a07ff8be2400ea877507826aefae0', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0/e4ae5cff26294126bfa9cacaeccb9016', '30', '000110/10/30', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('8376461b23d34a4189a698d0fbf9acc4', 'tProductRemove', '产品删除', '10', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:remove', '#', '236a07ff8be2400ea877507826aefae0', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0/8376461b23d34a4189a698d0fbf9acc4', '40', '000110/10/40', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

insert into sys_menu (id, menu_code, menu_name, sort, path, component, is_frame, is_cache, menu_type, visible, perms, icon, parent_id, parent_ids, tree_sort, tree_sorts, tree_level, tree_leaf, status, create_by, create_time, update_by, update_time, create_dept, update_ip, version, remark, del_flag)
values('c6f57b2092bb430d9779468c1cabd119', 'tProductExport', '产品查询', '50', '#', '', 1, 0, 'F', 0, 'shadd:tProduct:export', '#', '236a07ff8be2400ea877507826aefae0', '55445efde2034710ba9ba4df81a9ae2c/236a07ff8be2400ea877507826aefae0/c6f57b2092bb430d9779468c1cabd119', '50', '000110/10/50', '3', 'y', '0', '1', sysdate(), '1', sysdate(), '', '127.0.0.1', 0, '', '0');

-- 如果父节点为叶子节点，则更新为非叶子节点
update sys_menu set tree_leaf = 'n' where id = '55445efde2034710ba9ba4df81a9ae2c';

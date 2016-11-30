package com.billshare.services;

import com.billshare.dto.GroupDTO;
import com.billshare.utils.GroupsList;

public interface GroupService {
	public GroupDTO saveGroup(GroupDTO groupDTO);
	public GroupsList getList(String id);
	public GroupDTO update(GroupDTO dto);
}

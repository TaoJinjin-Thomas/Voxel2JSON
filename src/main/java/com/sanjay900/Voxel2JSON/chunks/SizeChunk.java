package com.sanjay900.Voxel2JSON.chunks;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import com.sanjay900.Voxel2JSON.utils.Utils;

public class SizeChunk extends Chunk{
	public BigDecimal x;
	public BigDecimal y;
	public BigDecimal z;
	public BigDecimal biggest;
	public SizeChunk(DataInputStream ds) throws IOException {
		super(ds);
		x = new BigDecimal(Utils.getInt(ds));
		y = new BigDecimal(Utils.getInt(ds));
		z = new BigDecimal(Utils.getInt(ds));
		biggest = x.max(y).max(z);
	}
}
package org.paracite.goblinking.levels;

import java.util.ArrayList;
import java.util.List;

import org.paracite.glframework.math.Vector2D;
import org.paracite.goblinking.model.EnemySpawner;
import org.paracite.goblinking.model.FloorWall;
import org.paracite.goblinking.model.World;

public class Level001 extends World {
	
	@Override
	public int returnWorldHeight() {
		return 10;
	}

	@Override
	public int returnWorldWidth() {
		return 10;
	}
	
	@Override
	public Vector2D returnHeroStartPos() {
		return new Vector2D(4.5f,4.5f);
	}
	
	@Override
	public FloorWall[][] returnFloorsWalls(int width, int height) {
		FloorWall[][] fw = new FloorWall[width][height];
		
		//Populate this thing with floor tiles first
		for (int i = 0; i < fw.length; i++){
			for (int j = 0; j < fw[i].length; j++){
				fw[i][j] = new FloorWall((float)i,(float)j,FloorWall.F);
			}
		}
		
		fw[3][9].type = FloorWall.DRC;
		fw[4][9].type = FloorWall.H;
		fw[5][9].type = FloorWall.DT;
		fw[6][9].type = FloorWall.H;
		fw[7][9].type = FloorWall.RE;
		
		fw[3][8].type = FloorWall.V;
		fw[5][8].type = FloorWall.V;
		
		fw[1][7].type = FloorWall.UE;
		fw[3][7].type = FloorWall.DE;
		fw[5][7].type = FloorWall.RT;
		fw[6][7].type = FloorWall.H;
		fw[7][7].type = FloorWall.H;
		fw[8][7].type = FloorWall.RE;
		
		fw[1][6].type = FloorWall.V;
		fw[5][6].type = FloorWall.V;
		
		fw[1][5].type = FloorWall.RUC;
		fw[2][5].type = FloorWall.H;
		fw[3][5].type = FloorWall.DT;
		fw[4][5].type = FloorWall.H;
		fw[5][5].type = FloorWall.C;
		fw[6][5].type = FloorWall.H;
		fw[7][5].type = FloorWall.DT;
		fw[8][5].type = FloorWall.H;
		fw[9][5].type = FloorWall.LDC;
		
		fw[3][4].type = FloorWall.V;
		fw[5][4].type = FloorWall.DE;
		fw[7][4].type = FloorWall.DE;
		fw[9][4].type = FloorWall.V;
		
		fw[2][3].type = FloorWall.LE;
		fw[3][3].type = FloorWall.ULC;
		fw[9][3].type = FloorWall.V;
		
		fw[5][2].type = FloorWall.UE;
		fw[9][2].type = FloorWall.V;
		
		fw[5][1].type = FloorWall.RUC;
		fw[6][1].type = FloorWall.H;
		fw[7][1].type = FloorWall.H;
		fw[8][1].type = FloorWall.H;
		fw[9][1].type = FloorWall.ULC;
		
		return fw;
	}	

	@Override
	public List<EnemySpawner> returnSpawnerList() {
		List<EnemySpawner> spawners = new ArrayList<EnemySpawner>();
		
		//TODO make some fuckin spawners.
		
		return spawners;
	}
}

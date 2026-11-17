package com.example.horns;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;

public class HornsModel extends Model<PlayerEntityRenderState> {
    public static final EntityModelLayer LAYER =
        new EntityModelLayer(Identifier.of("horns", "horns"), "main");

    public HornsModel(ModelPart root) {
        super(root, RenderLayers::entityTranslucent);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();
        addHorn(root, "left", 1);
        addHorn(root, "right", -1);
        return TexturedModelData.of(data, 16, 40);
    }

    private static void addHorn(ModelPartData root, String name, int side) {
        // 8 segments tapering from thick base (3.2) down to needle-sharp tip (0.5)
        float[] size  = {3.2f, 2.8f, 2.4f, 2.0f, 1.6f, 1.2f, 0.8f, 0.5f};
        // Starts angled wide outward (+), then curves smoothly inward (-) toward vertical points
        float[] roll  = {0.85f, 0.25f, -0.15f, -0.22f, -0.22f, -0.18f, -0.15f, -0.12f};
        // Sweeps forward (-) at the base, then curves upward and slightly back (+) at the tips
        float[] pitch = {-0.55f, -0.20f, 0.10f, 0.18f, 0.20f, 0.18f, 0.15f, 0.12f};
        float h = 2.6f;

        ModelPartData parent = root;
        for (int i = 0; i < 8; i++) {
            float w = size[i];
            int uvY = Math.min(i, 4) * 8; // maps cleanly onto your existing 16x40 texture
            ModelTransform t = (i == 0)
                ? ModelTransform.of(side * 3.0f, -5.8f, -1.5f, pitch[i], 0f, side * roll[i])
                : ModelTransform.of(0f, -h, 0f, pitch[i], 0f, side * roll[i]);
            parent = parent.addChild(name + i,
                ModelPartBuilder.create()
                    .uv(0, uvY)
                    .cuboid(-w / 2f, -h, -w / 2f, w, h, w),
                t);
        }
    }
}

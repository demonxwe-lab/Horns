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
        float[] size = {2.5f, 2.1f, 1.7f, 1.3f, 0.9f};
        // Slight outward angle at the base, then straight/slightly inward at the tips
        float[] roll = {0.28f, 0.08f, -0.05f, -0.08f, -0.08f};
        // Strong forward tilt at segments 0-1 (-), then sweeps up and back (+) on segments 2-4
        float[] pitch = {-0.65f, -0.25f, 0.45f, 0.50f, 0.40f};
        float h = 2.0f;

        ModelPartData parent = root;
        for (int i = 0; i < 5; i++) {
            float w = size[i];
            ModelTransform t = (i == 0)
                ? ModelTransform.of(side * 2.4f, -6.2f, -2.5f, pitch[i], 0f, side * roll[i])
                : ModelTransform.of(0f, -h, 0f, pitch[i], 0f, side * roll[i]);
            parent = parent.addChild(name + i,
                ModelPartBuilder.create()
                    .uv(0, i * 8)
                    .cuboid(-w / 2f, -h, -w / 2f, w, h, w),
                t);
        }
    }
}

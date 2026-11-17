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
        float[] size = {2.6f, 2.2f, 1.8f, 1.4f, 1.0f};
        // Positive bends outward, negative curves the tips back inward
        float[] roll = {0.45f, 0.10f, -0.12f, -0.18f, -0.20f};
        // Negative tilts forward at the base, positive sweeps up and back
        float[] pitch = {-0.25f, 0.15f, 0.25f, 0.25f, 0.20f};
        float h = 2.0f;

        ModelPartData parent = root;
        for (int i = 0; i < 5; i++) {
            float w = size[i];
            ModelTransform t = (i == 0)
                ? ModelTransform.of(side * 2.6f, -6.2f, -1.8f, pitch[i], 0f, side * roll[i])
                : ModelTransform.of(0f, -h, 0f, pitch[i], 0f, side * roll[i]);
            parent = parent.addChild(name + i,
                ModelPartBuilder.create()
                    .uv(0, i * 8)
                    .cuboid(-w / 2f, -h, -w / 2f, w, h, w),
                t);
        }
    }
}

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
        float[] size = {3.0f, 2.5f, 2.0f, 1.5f, 1.0f};       // segment width/depth
        float[] bend = {0.35f, 0.25f, 0.2f, 0.15f, 0.1f};  // outward curve per segment
        float h = 2.2f;

        ModelPartData parent = root;
        for (int i = 0; i < 5; i++) {
            float w = size[i];
            ModelTransform t = (i == 0)
                ? ModelTransform.of(side * 3.5f, -7.5f, 0f, 0.15f, 0f, side * bend[i])
                : ModelTransform.of(0f, -h, 0f, 0.08f, 0f, side * bend[i]);
            parent = parent.addChild(name + i,
                ModelPartBuilder.create()
                    .uv(0, i * 8)
                    .cuboid(-w / 2f, -h, -w / 2f, w, h, w),
                t);
        }
    }
}

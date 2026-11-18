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
        // 1. Base emerging straight out from the side of the head
        root.addChild(name + "_base",
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-1.5f, -1.5f, -1.5f, 3.0f, 3.0f, 3.0f),
            ModelTransform.of(side * 4.5f, -5.0f, -0.5f, 0f, 0f, 0f));

        // 2. Outer elbow stepping out and slightly up
        root.addChild(name + "_elbow",
            ModelPartBuilder.create()
                .uv(0, 8)
                .cuboid(-1.25f, -1.25f, -1.25f, 2.5f, 2.5f, 2.5f),
            ModelTransform.of(side * 6.0f, -6.5f, -0.5f, 0f, 0f, 0f));

        // 3. Tall vertical shaft rising straight up
        root.addChild(name + "_shaft",
            ModelPartBuilder.create()
                .uv(0, 16)
                .cuboid(-1.0f, -2.0f, -1.0f, 2.0f, 4.0f, 2.0f),
            ModelTransform.of(side * 6.5f, -8.8f, -0.5f, 0f, 0f, 0f));

        // 4. Tip hooking inward at the top
        root.addChild(name + "_tip",
            ModelPartBuilder.create()
                .uv(0, 28)
                .cuboid(-1.0f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f),
            ModelTransform.of(side * 5.2f, -10.3f, -0.5f, 0f, 0f, 0f));
    }
}

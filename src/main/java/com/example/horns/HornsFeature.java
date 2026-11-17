package com.example.horns;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HornsFeature extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    private static final Identifier TEXTURE = Identifier.of("horns", "textures/horns.png");
    private final HornsModel model;

    public HornsFeature(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> ctx,
                        LoadedEntityModels models) {
        super(ctx);
        this.model = new HornsModel(models.getModelPart(HornsModel.LAYER));
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light,
                       PlayerEntityRenderState state, float yaw, float pitch) {
        if (state.invisible) return;

        matrices.push();
        this.getContextModel().head.applyTransform(matrices);

        queue.submitModel(model, state, matrices,
            RenderLayer.getEntityTranslucent(TEXTURE),
            light, OverlayTexture.DEFAULT_UV, state.outlineColor, null);
        matrices.pop();
    }
}

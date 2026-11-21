package com.example.mutantsquid.client;

import com.example.mutantsquid.entity.RitualShellEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

public class RitualShellRenderer extends EntityRenderer<RitualShellEntity> {
    private static final Identifier TEX = new Identifier("mutantsquid", "textures/entity/ritual_shell.png");

    public RitualShellRenderer(EntityRendererFactory.Context ctx) { super(ctx); }

    @Override
    public Identifier getTexture(RitualShellEntity entity) { return TEX; }

    @Override
    public void render(RitualShellEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vcp, int light) {
        matrices.push();
        matrices.translate(0, 0.5, 0);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.age * 2f + tickDelta));
        Matrix4f m = matrices.peek().getPositionMatrix();
        VertexConsumer vc = vcp.getBuffer(RenderLayer.getEntityCutout(TEX));
        float s = 0.5f;
        float pulse = 1.0f + MathHelper.sin((entity.age + tickDelta) * 0.15f) * 0.1f;
        QuadHelper.box(vc, m, -s * pulse, 0, -s * pulse, s * pulse, s * 2 * pulse, s * pulse, light);
        matrices.pop();
    }
}

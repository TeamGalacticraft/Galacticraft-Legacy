/*
 * Copyright (c) 2023 Team Galacticraft
 *
 * Licensed under the MIT license.
 * See LICENSE file in the project root for details.
 */

package micdoodle8.mods.galacticraft.planets.asteroids.client.render.item;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import micdoodle8.mods.galacticraft.core.wrappers.ModelTransformWrapper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraftforge.common.model.TRSRTransformation;

public class ItemModelBeamReflector extends ModelTransformWrapper
{

    public ItemModelBeamReflector(IBakedModel modelToWrap)
    {
        super(modelToWrap);
    }

    @Override
    protected Matrix4f getTransformForPerspective(TransformType cameraTransformType)
    {
        if (cameraTransformType == TransformType.GUI)
        {
            Matrix4f ret = new Matrix4f();
            ret.setIdentity();
            Matrix4f mul = new Matrix4f();
            mul.setIdentity();
            Quat4f rot = TRSRTransformation.quatFromXYZDegrees(new Vector3f(30, 225, 0));
            mul.setRotation(rot);
            ret.mul(mul);
            mul.setIdentity();
            mul.setTranslation(new Vector3f(0.55F, 0.15F, 0.0F));
            ret.mul(mul);
            mul.setIdentity();
            mul.setScale(0.6F);
            ret.mul(mul);
            return ret;
        }

        if (cameraTransformType == TransformType.FIRST_PERSON_RIGHT_HAND || cameraTransformType == TransformType.FIRST_PERSON_LEFT_HAND)
        {
            float xTran = cameraTransformType == TransformType.FIRST_PERSON_LEFT_HAND ? -0.5F : 0.5F;
            Matrix4f ret = new Matrix4f();
            ret.setIdentity();
            Matrix4f mul = new Matrix4f();
            mul.setIdentity();
            Quat4f rot = TRSRTransformation.quatFromXYZDegrees(new Vector3f(0, -90, 0));
            mul.setRotation(rot);
            ret.mul(mul);
            mul.setIdentity();
            mul.rotY((float) (Math.PI));
            ret.mul(mul);
            mul.setIdentity();
            mul.setScale(0.3F);
            ret.mul(mul);
            mul.setIdentity();
            mul.setTranslation(new Vector3f(xTran, 0.5F, 0.5F));
            ret.mul(mul);
            return ret;
        }

        if (cameraTransformType == TransformType.THIRD_PERSON_RIGHT_HAND || cameraTransformType == TransformType.THIRD_PERSON_LEFT_HAND)
        {
            float xTran = cameraTransformType == TransformType.THIRD_PERSON_LEFT_HAND ? -0.3F : 0.6F;
            float zTran = cameraTransformType == TransformType.THIRD_PERSON_LEFT_HAND ? 0.3F : 0.3F;
            Vector3f trans = new Vector3f(xTran, 0.3F, zTran);
            Matrix4f ret = new Matrix4f();
            ret.setIdentity();
            Matrix4f mul = new Matrix4f();
            mul.setIdentity();
            Quat4f rot = TRSRTransformation.quatFromXYZDegrees(new Vector3f(70, 45, 0));
            mul.setRotation(rot);
            ret.mul(mul);
            mul.setIdentity();
            mul.setScale(0.35F);
            ret.mul(mul);
            mul.setIdentity();
            mul.setTranslation(trans);
            ret.mul(mul);
            return ret;
        }

        if (cameraTransformType == TransformType.GROUND)
        {
            Matrix4f ret = new Matrix4f();
            ret.setIdentity();
            Matrix4f mul = new Matrix4f();
            mul.setIdentity();
            mul.setScale(0.25F);
            ret.mul(mul);
            mul.setIdentity();
            mul.setTranslation(new Vector3f(0.5F, 0.25F, 0.5F));
            ret.mul(mul);
            return ret;
        }

        if (cameraTransformType == TransformType.FIXED)
        {
            Matrix4f ret = new Matrix4f();
            ret.setIdentity();
            Matrix4f mul = new Matrix4f();
            mul.setIdentity();
            mul.setScale(0.4F);
            ret.mul(mul);
            mul.setIdentity();
            mul.rotY(1.565F);
            ret.mul(mul);
            mul.setIdentity();
            mul.setTranslation(new Vector3f(0.5F, 0.0F, 0.5F));
            ret.mul(mul);
            return ret;
        }

        return null;
    }
}

<template>
  <v-container grid-list-md text-xs-center>
    <v-card
      class="mx-auto mask-info-card"
      outlined
    >
      <v-form column>
        <v-file-input
          accept="image/*"
          v-model="imagePath"
          label="Image"
          prepend-icon="camera_alt"
          @change="previewFiles"
          filled
        />

        <v-img v-if="imagePathNotEmpty()"
               height="64"
               width="64"
               class="image-preview"
               :src="image" />

        <v-text-field
          v-model="name"
          filled
          prepend-icon="format_align_left"
          label="Name"/>

        <v-textarea
          v-model="description"
          filled
          prepend-icon="format_align_justify"
          label="Description"/>

        <v-text-field
          v-model="quantity"
          filled
          prepend-icon="exposure_plus_1"
          label="Quantity"
          type="number"
        />

        <v-card-actions>
          <v-spacer />
          <v-btn color="primary" @click.native="add()">
            <v-icon left>add</v-icon>Submit
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import helper from '../assets/helper'

export default {
  data() {
    return {
      imagePath: null,
      name: null,
      description: null,
      quantity: null,
      image: null
    }
  },

  methods: {
    add () {
      console.log(this.imagePath)
    },

    imagePathNotEmpty () {
      return !helper.nullUndefinedOrBlank(this.imagePath)
    },

    previewFiles(file) {
      this.image = URL.createObjectURL(file)
    }
  }
}
</script>
<style>
  .mask-info-card {
    padding: 16px;
  }

  .image-preview {
    margin-left: 32px;
    margin-bottom: 24px;
  }
</style>
